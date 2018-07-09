package react.springsecurity.rssserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import react.springsecurity.rssserver.model.Poll;
import react.springsecurity.rssserver.payload.*;
import react.springsecurity.rssserver.repository.PollRepository;
import react.springsecurity.rssserver.repository.UserRepository;
import react.springsecurity.rssserver.repository.VoteRepository;
import react.springsecurity.rssserver.security.CurrentUser;
import react.springsecurity.rssserver.security.UserPrincipal;
import react.springsecurity.rssserver.service.PollService;
import react.springsecurity.rssserver.utils.AppConstants;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/polls")
public class PollController {

  @Autowired
  private PollRepository pollRepository;

  @Autowired
  private VoteRepository voteRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PollService pollService;

  @GetMapping
  public PagedResponse<PollResponse> getPolls(@CurrentUser UserPrincipal currentUser,
                                              @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                              @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
    return pollService.getAllPolls(currentUser, page, size);
  }

  @PostMapping
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<ApiResponse> createPoll(@Valid @RequestBody PollRequest pollRequest) {
    Poll poll = pollService.createPoll(pollRequest);

    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest().path("/{pollId}")
        .buildAndExpand(poll.getId()).toUri();

    return ResponseEntity.created(location)
        .body(new ApiResponse(true, "Poll Created Successfully"));
  }

  @GetMapping("/{pollId}")
  public PollResponse getPollById(@CurrentUser UserPrincipal currentUser,
                                  @PathVariable Long pollId) {
    return pollService.getPollById(pollId, currentUser);
  }

  @PostMapping("/{pollId}/votes")
  @PreAuthorize("hasRole('USER')")
  public PollResponse castVote(@CurrentUser UserPrincipal currentUser,
                               @PathVariable Long pollId,
                               @Valid @RequestBody VoteRequest voteRequest) {
    return pollService.castVoteAndGetUpdatedPoll(pollId, voteRequest, currentUser);
  }
}

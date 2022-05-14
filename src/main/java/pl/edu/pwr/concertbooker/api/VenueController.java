package pl.edu.pwr.concertbooker.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pwr.concertbooker.service.interfaces.IVenueService;

@RestController("/api/v1/venue")
@AllArgsConstructor
public class VenueController {
    private IVenueService venueService;
    
    
}

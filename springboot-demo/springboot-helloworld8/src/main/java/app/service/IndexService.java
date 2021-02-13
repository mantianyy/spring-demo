package app.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class IndexService {

    @Secured({"ROLE_ADMIN"})
    public String admin() { return "hello admin"; }

    @PreAuthorize("hasRole('ROLE_DBA')")
    public String dba() {
        return "hello dba";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    public String user() { return "hello user"; }

}

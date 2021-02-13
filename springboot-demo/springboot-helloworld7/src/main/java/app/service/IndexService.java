package app.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class IndexService {

    @Secured({"ROLE_ADMIN"})
    public String admin() { return "hello admin"; }

    @PreAuthorize("hasRole('ADMIN') AND hasRole('DBA')")
    public String dba() {
        return "hello dba";
    }

    @PreAuthorize("hasAnyRole('ROOT','ADMIN','DBA','USER')")
    public String user() { return "hello user"; }

}

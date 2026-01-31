package com.example.SecurityApp.utills;

import com.example.SecurityApp.entities.enums.Permission;
import com.example.SecurityApp.entities.enums.Role;
import io.micrometer.common.KeyValue;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.SecurityApp.entities.enums.Permission.*;
import static com.example.SecurityApp.entities.enums.Role.*;

public class PermissionMapping {

    private static final Map<Role , Set<Permission>> map= Map.of(
            USER, Set.of(USER_VIEW,POST_VIEW),
            CREATOR,Set.of(POST_CREATE , POST_UPDATE , USER_UPDATE),
            ADMIN, Set.of(POST_DELETE, POST_CREATE, POST_UPDATE, USER_UPDATE ,USER_DELETE , USER_CREATE)
    );

    public static Set<SimpleGrantedAuthority> getAuthorityForRole(Role role){
        return map.get(role).stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toSet());
    }
}

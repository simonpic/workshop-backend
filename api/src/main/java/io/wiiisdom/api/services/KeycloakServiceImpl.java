package io.wiiisdom.api.services;

import io.wiiisdom.api.config.KeycloakProperties;
import io.wiiisdom.api.model.Roles;
import io.wiiisdom.api.model.User;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class KeycloakServiceImpl implements KeycloakService {

    private final Keycloak keycloak;
    private final KeycloakProperties keycloakProperties;

    @Autowired
    public KeycloakServiceImpl(Keycloak keycloak, KeycloakProperties keycloakProperties) {
        this.keycloak = keycloak;
        this.keycloakProperties = keycloakProperties;
    }

    @Override
    public void createUser(User user) {
        RealmResource realm = keycloak.realm(keycloakProperties.getRealm());
        UserRepresentation userRepresentation = buildUserRepresentation(user);
        realm.users().create(userRepresentation);
    }

    private UserRepresentation buildUserRepresentation(User user) {
        UserRepresentation userRepresentation = new UserRepresentation();

        userRepresentation.setEmail(user.getEmail());
        userRepresentation.setUsername(user.getPseudo());
        userRepresentation.setRealmRoles(Collections.singletonList("administrator"));
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(true);

        setUserAttributes(userRepresentation, user);

        CredentialRepresentation credentials = buildCredentials(user.getPassword());
        userRepresentation.setCredentials(Collections.singletonList(credentials));

        return userRepresentation;
    }

    private void setUserAttributes(UserRepresentation userRepresentation, User user) {
        Map<String, List<String>> attributes = new HashMap<>();
        attributes.put("id", Collections.singletonList(user.getId()));
        userRepresentation.setAttributes(attributes);
    }

    private CredentialRepresentation buildCredentials(String password) {
        CredentialRepresentation credentials = new CredentialRepresentation();
        credentials.setTemporary(false);
        credentials.setType("password");
        credentials.setValue(password);
        return credentials;
    }
}

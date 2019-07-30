package pl.javasoft.springboot2keycloack;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.javasoft.springboot2keycloack.dto.LoginDTO;
import pl.javasoft.springboot2keycloack.dto.ResponseDTO;

@RestController
@AllArgsConstructor
public class UserController {

    private final OpenApiClient openApiClient;


    @CrossOrigin(allowCredentials = "true", value = "http://localhost:3000")
    @GetMapping("/user")
    public UserDTO user(Principal keycloakPrincipal){
        return new UserDTO(keycloakPrincipal);
    }

    @PostMapping("/login")
    public ResponseDTO login(@RequestBody LoginDTO loginDTO){
        return openApiClient.login(loginDTO);
    }

    @GetMapping(path = "/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "/";
    }

    class UserDTO {
        public UserDTO(Principal keycloakPrincipal) {
            this.email = keycloakPrincipal.getName();
            this.fisrtName = "";
            this.lastName="";
        }

        private String fisrtName;
        private String lastName;
        private String email;

        public String getFisrtName() {
            return fisrtName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getEmail() {
            return email;
        }
    }
}

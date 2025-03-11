package com.desafio.calculoimposto.infra.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class SecurityConfigTests {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private MockMvc mockMvc;

//   @Test
//   void testSecurityFilterChain() throws Exception {
//       mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

//       // Teste de acesso permitido à página Swagger
//       mockMvc.perform(SecurityMockMvcRequestBuilders.get("/swagger-ui.html"))
//               .andExpect(status().isOk());

//       // Teste de acesso negado a uma página protegida sem autenticação
///        mockMvc.perform(SecurityMockMvcRequestBuilders.get("/protected-page"))
///                .andExpect(status().isUnauthorized());

//       // Teste de login com credenciais válidas
//       mockMvc.perform(formLogin("/login").user("user").password("password"))
//               .andExpect(authenticated());

//       // Teste de logout
//       mockMvc.perform(logout("/logout"))
//               .andExpect(unauthenticated());
 //   }

    @Test
    void testPasswordEncoder() {
        // Teste para verificar se o PasswordEncoder é do tipo BCryptPasswordEncoder
        String rawPassword = "password";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // Verifica se a senha codificada corresponde à senha original
        assert passwordEncoder.matches(rawPassword, encodedPassword);
    }
}

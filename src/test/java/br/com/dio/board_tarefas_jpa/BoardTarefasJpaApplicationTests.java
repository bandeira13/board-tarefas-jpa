package br.com.dio.board_tarefas_jpa;

import br.com.dio.board_tarefas_jpa.dto.BoardDTO;
import br.com.dio.board_tarefas_jpa.service.BoardService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
class BoardTarefasJpaApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private BoardService boardService;

	@Test
	void testCreateBoardAndCard() throws Exception {

		String boardJson = """
				    { "name": "Quadro de Teste Automatizado" }
				""";

		mockMvc.perform(post("/boards")
						.contentType(MediaType.APPLICATION_JSON)
						.content(boardJson))
				.andExpect(status().isCreated());


		BoardDTO board = boardService.findById(1L);
		Long idColunaFazer = board.columns().stream()
				.filter(c -> c.name().equals("A Fazer"))
				.findFirst()
				.orElseThrow()
				.id();

		System.out.println("O ID da coluna 'A Fazer' é: " + idColunaFazer);


		String cardJson = String.format("""
				    {
				        "title": "Aprender Testes",
				        "description": "Automatizando tudo com JUnit",
				        "columnId": %d
				    }
				""", idColunaFazer);

		mockMvc.perform(post("/cards")
						.contentType(MediaType.APPLICATION_JSON)
						.content(cardJson))
				.andExpect(status().isCreated());


		mockMvc.perform(get("/boards/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Quadro de Teste Automatizado"))
				.andExpect(jsonPath("$.columns[0].cards[0].title").value("Aprender Testes"));

		System.out.println("✅ Teste finalizado com sucesso! A tarefa foi criada e encontrada.");
	}
}


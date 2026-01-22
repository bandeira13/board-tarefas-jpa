# 📋 Board de Tarefas (Kanban) com Spring Boot

Este projeto é um gerenciador de tarefas estilo Kanban Fullstack, desenvolvido para demonstrar a implementação de regras de negócio complexas, integridade de dados e testes automatizados (QA).

## 🚀 Funcionalidades Principais

* **Gestão de Quadros:** Criação dinâmica de quadros com colunas padrão (A Fazer, Em Progresso, Concluído).
* **Kanban Interativo:** Movimentação de tarefas (Drag & Drop) entre colunas com persistência automática.
* **Feature "Concluir Quadro":** Permite marcar um projeto como finalizado, alterando visualmente seu status e bloqueando edições.
* **UX Aprimorada:**
    * **Modais Inteligentes:** O formulário adapta o texto ("O que foi feito?" vs "O que precisa ser feito?") dependendo da coluna.
    * **Visualização de Detalhes:** Clique nas tarefas para ler descrições longas sem cortes.
* **Exclusão em Cascata:** Correção de bug (`CascadeType.ALL`) permitindo deletar colunas com itens vinculados sem erros de integridade.

## 🛠️ Tecnologias Utilizadas

* **Backend:** Java 17, Spring Boot 3, JPA / Hibernate.
* **Frontend:** HTML5, CSS3, JavaScript (Vanilla), Bootstrap 5.
* **Banco de Dados:** H2 Database (Memória/Arquivo).
* **QA & Testes:**
    * **JUnit 5 & MockMvc:** Testes de integração da API (Controller/Service).
    * **Selenium WebDriver:** Testes End-to-End (E2E) simulando a jornada do usuário no navegador.

## 🧪 Estrutura de Testes (QA)

O projeto segue a pirâmide de testes para garantir qualidade:

1.  **Testes de Integração (`BoardTarefasJpaApplicationTests`):** Validam o contrato da API, criação de quadros e persistência das colunas padrão.
2.  **Testes E2E (`SeleniumBoardTest`):** Automatizam a interface gráfica (Chrome Headless), garantindo que o botão de "Criar" e a renderização da lista funcionem como esperado para o usuário final.

## ▶️ Como Executar

1.  Clone o repositório.
2.  Execute o projeto via Maven/Gradle:
    ```bash
    ./gradlew bootRun
    ```
3.  Acesse no navegador: `http://localhost:8080`

---
*Desenvolvido com foco em boas práticas de Engenharia de Software e Qualidade (QA).*
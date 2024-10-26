# Trabalho AV2 de Sistemas Operacionais

## Pré-requisitos

Certifique-se de que os seguintes requisitos estão atendidos antes de iniciar a instalação:

- Java JDK 8 ou superior instalado. Você pode verificar a instalação com o comando:
   ```bash
   java -version
   ```
  
## Instalação

Este projeto é uma simulação de algoritmos de substituição de páginas de memória, desenvolvido como parte da disciplina de Sistemas Operacionais. Ele permite a execução e visualização dos algoritmos de gerenciamento de memória via CLI (linha de comando) e GUI (interface gráfica).

1. Clone o repositório:
   ```bash
   git clone https://github.com/pmas98/SO.git
   ```
2. Entre no diretório:
   ```bash
   cd SO
   ```
2. Faça a compilação do projeto:
   ```bash
   javac --release 8 -d out/production/SOProjetoAV2 src/main/java/com/SOProjetoAV2/*.java src/main/java/com/SOProjetoAV2/algorithms/*.java
   ```
3. Rode o programa:
   ```bash
   java -cp out/production/SOProjetoAV2 main.java.com.SOProjetoAV2.Simulador
   ```

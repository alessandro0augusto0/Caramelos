# Projeto: Caramelos 🍬

**Disciplina:** Projetos e Análise de Algoritmos  
**Professor:** Douglas Castilho  
**Período:** 6º  
**Curso:** Engenharia de Computação - IFSULDEMINAS, Campus Poços de Caldas  
**Competição:** Maratona de Programação da SBC - ICPC 2024  

## 📘 Descrição do Problema

Alice e Bob são irmãos gêmeos que, em seu aniversário, receberam várias sacolas cheias de caramelos de seus convidados. Porém, eles querem dividir os caramelos de forma justa, considerando a quantidade em cada sacola e a ordem em que são distribuídos. A regra é simples: a sacola é dada à pessoa com menos caramelos até aquele momento, com Alice recebendo em caso de empate. Mas existe uma questão: dependendo da ordem das sacolas, a divisão pode ser desigual.

Este programa tem o objetivo de encontrar uma ordem de distribuição em que ambos recebam a mesma quantidade de caramelos. Caso não seja possível, o programa retorna -1, indicando que não há uma solução equilibrada.

## 🎯 Objetivo

Desenvolver uma solução eficiente para determinar uma ordem de sacolas que possibilite a divisão igualitária dos caramelos entre Alice e Bob.

## 📥 Entrada de Dados

Primeira Linha: Um número inteiro N (1 < N < 100), indicando o número de sacolas de caramelos.  
Segunda Linha: N inteiros que representam a quantidade de caramelos em cada sacola a[i] (1 < a[i] < 100).

## 📤 Saída de Dados

Saída: Retorna uma única linha com uma sequência de números a[i] ordenados para que os caramelos sejam divididos igualmente entre Alice e Bob.  
Caso seja impossível dividir igualmente, o programa imprime -1.

## 🛠️ Implementação

Este projeto foi desenvolvido em Java, com o uso de programação dinâmica para verificar se é possível dividir as sacolas de forma equilibrada. A lógica é construída em torno do uso de tabelas de valores booleanos (pd[i][j]), que indicam se uma soma específica é atingível com as sacolas dadas.

## 👨‍💻 Estrutura do Código

O código está organizado em duas partes principais:

1. **Resolução do Problema**: Algoritmo de programação dinâmica que determina se uma divisão igualitária é possível e, se sim, apresenta a ordem das sacolas.
2. **Interface**: Exibe uma interface simples onde o usuário insere a quantidade de sacolas e a quantidade de caramelos em cada uma, além de visualizar o resultado da divisão.

## 💻 Interface Gráfica

A interface gráfica foi criada para simplificar a interação do usuário com o programa, seguindo os prints abaixo:

### Print 1: Interface Inicial
Na primeira tela, o usuário insere a quantidade de sacolas que deseja dividir.

![Caramelos1](https://github.com/user-attachments/assets/b0f16614-9c78-4423-8da9-5fcc5a75f90b)

### Print 2: Inserção de Caramelos
O usuário especifica a quantidade de caramelos em cada sacola.

![Caramelos2](https://github.com/user-attachments/assets/1e877901-7ec8-4fe5-b352-086ce7646309)

### Print 3: Resultado e Repetição
Após calcular a distribuição, o programa exibe o resultado e permite realizar novos cálculos com uma nova quantidade de sacolas.

![Caramelos3](https://github.com/user-attachments/assets/affb66cf-0b3f-4593-8430-14bb102e1474)

## 🚀 Executando o Programa

### Pré-requisitos

Para executar o programa, é necessário ter o Java Development Kit (JDK) instalado em seu sistema. Recomenda-se uma versão a partir do JDK 8.

### Passo a Passo

Clone o repositório para seu ambiente local:

```bash
git clone https://github.com/alessandro0augusto0/Caramelos.git
```

Compile o programa:

```bash
javac Caramelos.java
```

Execute o programa:

```bash
java Caramelos
```

## 🔄 Lógica do Programa

- **Verificação de Divisibilidade**: O programa verifica inicialmente se o total de caramelos é par. Se não for, a divisão igualitária é impossível.
- **Programação Dinâmica**: Um array de booleanos (pd) mapeia as combinações de somas possíveis com as sacolas, determinando se uma divisão com soma igual é atingível.
- **Alocação de Sacolas**: Se possível, o programa gera uma ordem de sacolas para que Alice e Bob terminem com o mesmo número de caramelos.

## ⚙️ Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues para relatar bugs ou propor melhorias.

Para contribuir:

1. Realize um fork do repositório.
2. Crie um branch para sua nova feature:
    ```bash
    git checkout -b feature/NovaFeature
    ```
3. Envie o Pull Request.

## 📜 Licença

Este projeto é licenciado sob a [Licença MIT](https://opensource.org/licenses/MIT).
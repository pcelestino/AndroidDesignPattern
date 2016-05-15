# Android Design Pattern
Projeto simples de cadastro de vendas e emissão de nota fiscal, utilizando os padrões Strategy, Chain Of Responsibility, Template Method, Build e Observer

### PADRÃO STRATEGY

**Interface** - IImposto
**Classes** - ImpostoIcms, ImpostoIss, CalculadorDeImpostos, Orcamento

ICMS e ISS são estratégias de cálculo de Imposto
Existe uma interface (Imposto), é implementado estratégias embaixo dessa interface (ImpostoIcms e ImpostoIss)
e é passado essas estratégias para o resto do sistema, rodando assim de uma maneira genérica
independente de um imposto específico que criado


### PADRÃO CHAIN OF RESPONSIBILITY

**Interface** - IDesconto
**Classes** - Orcamento, Item, DescontoPorMaisDeCincoItens, DescontoPorMaisDeQuinhentosReais, SemDesconto, CalculadorDeDescontos

Existe uma sequência de algorítmos, porém nem todos deverão ser executados, 
existe uma lógica para saber se eu devo executá-lo ou não e ao invés de colocar toda essa lógica em uma classe só
é separado em várias classes, onde cada uma sabe sua regra de negócio e sabe quando ela tem que ser executada 
passando ou não a responsabilidade para a próxima classe


### PADRÃO TEMPLATE METHOD

**Classe Abstrata** - TemplateDeImpostoCondicional
**Classes** - ImpostoIkcv, ImpostoIcpp

É criado uma classe abstrata (TemplateDeImpostoCondicional) com métodos abstratos 
e um método que implementa a lógica de negócio das classes onde a lógica se repete (ImpostoIkcv, ImpostoIcpp)


### PADRÃO BUILDER

**Classes** - NotaFiscal, NotaFiscalBuilder

NotaFiscal é uma classe mais complexa de ser criada, com vários parametros, então essa complexidade
é escondida em uma classe responsável por gerar NotasFiscais


### PADRÃO OBSERVER

**Interface** - IAcaoAposGerarNota
**Classes** - EnviadorDeEmail, EnviadorDeSms, SalvaNoBanco

Motivação - Permite que objetos interessados sejam avisados da mudança de estado 
ou outros eventos ocorrendo num outro objeto(Ao construir a NotaFiscal em NotaFiscalBuilder).

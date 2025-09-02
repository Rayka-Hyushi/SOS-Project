# SOS-Project

O Service Order System (SOS) é uma aplicação web destinada a auxiliar técnicos, sejam de grandes empresas ou autônomos, 
na organização, controle e acompanhamento de ordens de serviço de seus clientes. A aplicação permitirá o cadastro de 
clientes, serviços, técnicos e ordens, possibilitando o controle do status de cada atendimento. Na ordem de serviço, o 
técnico pode visualizar várias informações úteis, além de poder alterar o status de realização da solicitação.

# Regras de Negócio

1. Cada usuário é responsável apenas pelos clientes, serviços e ordens de serviço que ele mesmo cadastrou, não sendo 
permitido o acesso ou gerenciamento de registros de outros usuários.
2. Toda ordem de serviço deve estar vinculada a um cliente previamente cadastrado.
3. Uma ordem de serviço pode conter um ou mais serviços relacionados.
4. O status de uma ordem de serviço deve obrigatoriamente assumir apenas um dos seguintes estados: Aberta, Em Andamento, 
Concluída e Finalizada.
5. Não é permitido excluir uma OS, caso já estiver marcada como Concluída ou possuir serviços associados.
6. O valor total de uma ordem de serviço deve ser definido pela soma dos serviços vinculados, considerando também 
descontos aplicados e valores extras adicionados.
7. A data de abertura de uma ordem de serviço deve ser registrada no momento da sua criação. A data de finalização deve 
ser registrada somente quando a ordem for finalizada.
8. Cada ordem de serviço deve ter obrigatoriamente um técnico responsável associado.
9. É obrigatório que seja possível identificar e consultar ordens de serviço por cliente, status ou data.
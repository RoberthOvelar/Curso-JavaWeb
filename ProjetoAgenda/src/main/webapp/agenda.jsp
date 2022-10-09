<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
	ArrayList<JavaBeans> listaContatos = (ArrayList<JavaBeans>) request.getAttribute("contatos");
%>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Agenda</title>
		<link rel="stylesheet" href="style.css">
	</head>
	<body>
		<h1>Agenda de contados</h1>
		<table id="tabela">
			<thead>
				<tr>
					<th>ID</th>
					<th>Nome</th>
					<th>Fone</th>
					<th>E-mail</th>
				</tr>
			</thead>
			<tbody>
				<% for(int i = 0; i < listaContatos.size(); i++){ %>
					<tr>
						<td><%=listaContatos.get(i).getIdcon() %></td>
						<td><%=listaContatos.get(i).getNome() %></td>
						<td><%=listaContatos.get(i).getFone() %></td>
						<td><%=listaContatos.get(i).getEmail() %></td>
					</tr>
				<%} %>
			</tbody>
		</table>
		<a href="novo-contato.html">Novo contato</a>
	</body>
</html>
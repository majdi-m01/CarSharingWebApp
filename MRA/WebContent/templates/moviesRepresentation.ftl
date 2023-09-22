<#include "header.ftl">

<b>Welcome to our little demonstration on the MRA Webapp</b>
<h3> All Movies List </h3>

<table id="availableMovies">
	<tr>
		<th>CLICK TO MAKE RATING</th>
		<th>title</th>
		<th>actor</th>
		<th>director</th>
		<th>publishedDate</th>
	</tr>
	<#list availableMovies as movies>
	<tr>
		<td><a href="registeredUserGUI?action=makeRating&amp;title=${movies.title}" title="Make Rating">RATE</a></td>
		<td>${movies.title}</td>
		<td>${movies.actor}</td>
		<td>${movies.director}</td>
		<td>${movies.publishedDate}</td>
	</tr>
	</#list>
</table>
<#include "footer.ftl">
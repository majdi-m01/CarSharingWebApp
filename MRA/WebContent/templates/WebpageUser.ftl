<#include "header.ftl">
<b>Welcome to our little demonstration on the MRA Webapp</b><br><br>
<h3> Register User </h3>
<form method="POST" action="webpageUser?action=register">
	<fieldset id="register">
		<legend>Required Information</legend>
		<div>
			<label>Username</label>
			<input type="text" name="username">
	    </div>

		<div>
			<label>Email</label>
			<input type="email" name="email">
	    </div>

		<div>
			<label>Age</label>
			<input type="text" name="age">
	    </div>
	</fieldset>
	<button type="submit" id="submit">Submit</button>
</form>
<#include "footer.ftl">

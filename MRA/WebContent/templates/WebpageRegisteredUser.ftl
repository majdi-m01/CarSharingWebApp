<#include "header.ftl">
<b>Welcome to our little demonstration on the MRA Webapp</b><br><br>
<h3> Add Movie </h3>
<form method="POST" action="webpageRegisteredUser?action=addMovie">
	<fieldset id="browseAvailableMovies">
		<legend>Required Information</legend>
		<div>
			<label>Title</label>
			<input type="text" name="title">
	    </div>

		<div>
			<label>Actor</label>
			<input type="text" name="actor">
	    </div>

		<div>
			<label>Director</label>
			<input type="text" name="director">
	    </div>
	    <div>
			<label>publishedDate</label>
			<input type="date" name="publishedDate">
	    </div>
	</fieldset>
	<button type="submit" id="SelectRUWebpage" name="SelectRUWebpage" value="Submit">Submit!</button>
</form>
<#include "footer.ftl">

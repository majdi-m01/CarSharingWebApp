<#include "header.ftl">

<b>Welcome to our little demonstration on the MRA Webapp</b><br><br>

<form method="POST" action="guestgui?action=makeRating">
	<fieldset id="makeRating">
		<legend>Required Information</legend>
		<div>
			<label>Title</label>
			<input type="text" name="Title">
	    </div>

		<div>
			<label>Rating</label>
			<input type="text" name="Rating">
	    </div>
		<div>
			<label>Comment</label>
			<input type="text" name="Comment">
	    </div>
	    <div>
	    	<label>ruID</label>
	    	<input type="text" name="ruID">
	    </div>
	</fieldset>
	<input type="hidden" value="${title}" name="title">
	<button type="submit" id="submit">Submit</button>
</form>
<#include "footer.ftl">
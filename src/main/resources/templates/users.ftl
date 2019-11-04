<#import "parts/common.ftl" as C>
<@C.page>
User page
<div>
<span>${USER}.firstName}</span>
<i>${USER.secondName}</i>
<strong>${USER.age}</strong>
</div>
<a href="/logout">Logout</a>
<a href="/addbill">Add bill</a>
</@C.page>
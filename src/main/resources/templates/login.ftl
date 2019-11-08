<#import "parts/common.ftl" as C>
<#import "parts/login.ftl" as L>

<@C.page>
Login page
<@L.login "/login" />
<a href="/registration">Регистрация</a>
<a href="/main">Назад</a>
</@C.page>
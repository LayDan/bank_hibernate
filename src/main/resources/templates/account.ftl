<#import "parts/common.ftl" as C>
<@C.page>
<div>User page</div>

<div>
    <table>
        <tr>
            <td>
                <a href="/addbill">Add bill</a>
            </td>
            <td>
                <a href="/addMoney">Add money to bill</a>
            </td>
        </tr>

    </table>
</div>

Лист счетов :
<#list bills as b>
<div>
    <i>${b.number_card}</i>
    <i>${b.currency}</i>
    <i>${b.amoung}</i>
</div>
<#else>
no bills
</#list>

</@C.page>
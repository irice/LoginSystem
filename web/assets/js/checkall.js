/**
 * Created by 11456 on 2016/11/7.
 */

//ѡ��ȫѡ��ť�������checkboxȫ��ѡ��
var selAll = document.getElementById("selAll");
function selectAll()
{
    var obj = document.getElementsByName("checkAll");
    if(document.getElementById("selAll").checked == false)
    {
        for(var i=0; i<obj.length; i++)
        {
            obj[i].checked=false;
        }
    }else
    {
        for(var i=0; i<obj.length; i++)
        {
            obj[i].checked=true;
        }
    }

}

//��ѡ�����е�ʱ��ȫѡ��ť�Ṵ��
function setSelectAll()
{
    var obj=document.getElementsByName("checkAll");
    var count = obj.length;
    var selectCount = 0;

    for(var i = 0; i < count; i++)
    {
        if(obj[i].checked == true)
        {
            selectCount++;
        }
    }
    if(count == selectCount)
    {
        document.all.selAll.checked = true;
    }
    else
    {
        document.all.selAll.checked = false;
    }
}

//��ѡ��ť
function inverse() {
    var checkboxs=document.getElementsByName("checkAll");
    for (var i=0;i<checkboxs.length;i++) {
        var e=checkboxs[i];
        e.checked=!e.checked;
        setSelectAll();
    }
}


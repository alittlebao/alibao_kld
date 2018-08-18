:===================以管理员身份运行代码=======================
@echo off
>nul 2>&1 "%SYSTEMROOT%\system32\cacls.exe" "%SYSTEMROOT%\system32\config\system"
if '%errorlevel%' NEQ '0' (
goto UACPrompt
) else ( goto gotAdmin )
:UACPrompt
echo Set UAC = CreateObject^("Shell.Application"^) > "%temp%\getadmin.vbs"
echo UAC.ShellExecute "%~s0", "", "", "runas", 1 >> "%temp%\getadmin.vbs"
"%temp%\getadmin.vbs"
exit /B
:gotAdmin
if exist "%temp%\getadmin.vbs" ( del "%temp%\getadmin.vbs" )
:===================以管理员身份运行代码结束======================



:===================直接使用命令运行==========================

netsh interface ip set address name = "以太网" source = dhcp
netsh interface ip set dns name = "以太网" source = dhcp

:===================直接使用命令运行==========================



goto han
============这里开始是批量注释内容==========================
============带提示的手动修改ip命令=========================

Echo off 
echo  自动获取IP地址....
netsh interface ip set address name = "以太网" source = dhcp
echo  自动获取DNS服务器....
netsh interface ip set dns name = "以太网" source = dhcp
Echo 自动获取IP成功,按任一键后,就可以使用外网了……
Pause

===========下一句结束批量注释=================================
:han
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

Netsh interface IP Set Addr "以太网" Static 192.168.114.233 255.255.255.0 192.168.114.254 1
Netsh interface IP Set dns "以太网" static 223.5.5.5 primary
Netsh interface IP add dns "以太网" 114.114.114.114

:===================直接使用命令运行==========================



goto han
============这里开始是批量注释内容==========================
============带提示的手动修改ip命令=========================

Echo off
echo  手动设置IP地址....
Netsh interface IP Set Addr "以太网" Static 192.168.114.233 255.255.255.0 192.168.114.254 1
echo  手动设置DNS地址....
Netsh interface IP Set dns "以太网" static 223.5.5.5 primary
echo  手动设置备份DNS地址....
Netsh interface IP add dns "以太网" 114.114.114.114
Echo 设置成功 您的IP为192.168.114.233,按任一键后,就可以使用内网了…… 
Pause 

===========下一句结束批量注释=================================
:han
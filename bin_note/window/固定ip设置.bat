:===================�Թ���Ա������д���=======================
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
:===================�Թ���Ա������д������======================



:===================ֱ��ʹ����������==========================

Netsh interface IP Set Addr "��̫��" Static 192.168.114.233 255.255.255.0 192.168.114.254 1
Netsh interface IP Set dns "��̫��" static 223.5.5.5 primary
Netsh interface IP add dns "��̫��" 114.114.114.114

:===================ֱ��ʹ����������==========================



goto han
============���￪ʼ������ע������==========================
============����ʾ���ֶ��޸�ip����=========================

Echo off
echo  �ֶ�����IP��ַ....
Netsh interface IP Set Addr "��̫��" Static 192.168.114.233 255.255.255.0 192.168.114.254 1
echo  �ֶ�����DNS��ַ....
Netsh interface IP Set dns "��̫��" static 223.5.5.5 primary
echo  �ֶ����ñ���DNS��ַ....
Netsh interface IP add dns "��̫��" 114.114.114.114
Echo ���óɹ� ����IPΪ192.168.114.233,����һ����,�Ϳ���ʹ�������ˡ��� 
Pause 

===========��һ���������ע��=================================
:han
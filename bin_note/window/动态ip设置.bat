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

netsh interface ip set address name = "��̫��" source = dhcp
netsh interface ip set dns name = "��̫��" source = dhcp

:===================ֱ��ʹ����������==========================



goto han
============���￪ʼ������ע������==========================
============����ʾ���ֶ��޸�ip����=========================

Echo off 
echo  �Զ���ȡIP��ַ....
netsh interface ip set address name = "��̫��" source = dhcp
echo  �Զ���ȡDNS������....
netsh interface ip set dns name = "��̫��" source = dhcp
Echo �Զ���ȡIP�ɹ�,����һ����,�Ϳ���ʹ�������ˡ���
Pause

===========��һ���������ע��=================================
:han
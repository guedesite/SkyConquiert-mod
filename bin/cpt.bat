@echo off 
set /a nbFichiers=0 
for %%a in (*.*) do set /a nbFichiers+=1 
echo %nbFichiers%
timeout /t -1 /nobreak >NUL
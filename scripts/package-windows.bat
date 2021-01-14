if exist "..\release-windows" rmdir /S /Q "..\release-windows"
mkdir "..\release-windows"
copy ..\Valestrom.jar ..\release-windows\Valestrom.jar

echo d | xcopy /s /e /y ..\Valestrom\Samples\test\main\resources\programs ..\release-windows\samples
echo d | xcopy /s /e /y ..\Valestrom\Samples\test\main\resources\libraries ..\release-windows\samples\libraries
echo d | xcopy /s /e /y ..\benchmarks\BenchmarkRL\vale ..\release-windows\BenchmarkRL
echo d | xcopy /s /e /y ..\Midas\src\builtins ..\release-windows\builtins
copy ..\Midas\valec.py ..\release-windows\valec.py
copy ..\Midas\vstl\* ..\release-windows\vstl
copy ..\Midas\x64\Release\Midas.exe ..\release-windows\Midas.exe
copy ..\Midas\x64\Release\LLVM-C.dll ..\release-windows\LLVM-C.dll
copy releaseREADME.txt ..\release-windows\README.txt

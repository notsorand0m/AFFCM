# AFFCM (AI Full File Control Manager)
--------------------------------------------
### AI file path recommendation for specific used selected file
### Customizable AI model for more versatility
### AES Encryption/Decryption
### Basic database .json system included 
### Light/Dark Themes
### Cross platform
--------------------------------------------

MacOS Support
[Download .dmg file](https://www.dropbox.com/scl/fi/wfuog4skircf9b6380z79/AFFCM-1.0.dmg?rlkey=gjcwxcb869jc8edmu4uzo0c1v&st=m228g518&dl=0)

Windows Support
[Downloads .exe file](https://www.dropbox.com/scl/fi/2aw5xrchtfofb5rt6mwb9/AFFCM.zip?rlkey=t8c990597hozxmrbafckn5847&st=3askx9zj&dl=0)

--------------------------------------------
**Used libraries:**
  - javafx
  - gson-2.10.1
  - llama-4.1.0
  - slf4j-api-1.7.36
--------------------------------------------
### Build local .dmg
jpackage \                            
  --name AFFCM \                                                
  --app-version 1.0 \
  --type dmg \           
  --input libs \
  --main-jar AFFCM.jar \
  --main-class com.affcm.Main \
  --java-options "-Xmx512m" \
  --dest dist \
--icon AFFCM.icns

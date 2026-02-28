# AFFCM
## (AI Full File Control Manager)
## MacOS & Windows Support

### AI file path recommendation for specific used selected file
### Customizable AI model for more versatility
### AES Encryption/Decryption
### Basic database .json system included 
### Light/Dark Themes
### Cross platform


**Used libraries:**
  - javafx
  - gson-2.10.1
  - llama-4.1.0
  - slf4j-api-1.7.36

MacOS Support
[Download .dmg file](https://www.dropbox.com/scl/fi/4tb16twkwv4ufvq5nbc1i/AFFCM-1.0.dmg?rlkey=avgpielym2ll268nljl6cqmrm&st=2jdn3tpj&dl=0)

Windows Support
[Downloads .exe file](https://www.dropbox.com/scl/fi/2aw5xrchtfofb5rt6mwb9/AFFCM.zip?rlkey=t8c990597hozxmrbafckn5847&st=3askx9zj&dl=0)

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

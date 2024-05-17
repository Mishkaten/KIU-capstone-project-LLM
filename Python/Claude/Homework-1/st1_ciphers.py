# Atbash Cipher
def encryptAtbashCipher(plainText):
    encryptedText = ""
    for char in plainText:
        if char.isalpha():
            if char.islower():
                encryptedText += chr(ord('a') + ord('z') - ord(char))
            else:
                encryptedText += chr(ord('A') + ord('Z') - ord(char))
        else:
            encryptedText += char
    return encryptedText

def decryptAtbashCipher(encryptedText):
    return encryptAtbashCipher(encryptedText)


# Caesar Cipher
def encryptCaesarCipher(plainText, key):
    encryptedText = ""
    mapping = {
        '.': ',', ',': '.', '!': '?', '?': '!',
        '0': '1', '1': '0', '2': '3', '3': '2', '4': '5',
        '5': '4', '6': '7', '7': '6', '8': '9', '9': '8'
    }
    for char in plainText:
        if char.isalpha():
            if char.isupper():
                encryptedText += chr((ord(char) - ord('A') + key) % 26 + ord('A'))
            else:
                encryptedText += chr((ord(char) - ord('a') + key) % 26 + ord('a'))
        else:
            encryptedText += mapping.get(char, char)
    return encryptedText

def decryptCaesarCipher(encryptedText, key):
    return encryptCaesarCipher(encryptedText, 26 - key)


# Vigenère Cipher
def encryptVigenereCipher(plainText, keyList):
    encryptedText = ""
    keyNum = len(keyList)
    mapping = {
        '.': ',', ',': '.', '!': '?', '?': '!',
        '0': '1', '1': '0', '2': '3', '3': '2', '4': '5',
        '5': '4', '6': '7', '7': '6', '8': '9', '9': '8'
    }
    for i, char in enumerate(plainText):
        if char.isalpha():
            key = keyList[i % keyNum]
            if char.isupper():
                encryptedText += chr((ord(char) - ord('A') + key) % 26 + ord('A'))
            else:
                encryptedText += chr((ord(char) - ord('a') + key) % 26 + ord('a'))
        else:
            encryptedText += mapping.get(char, char)
    return encryptedText

def decryptVigenereCipher(encryptedText, keyList):
    keyNum = len(keyList)
    decryptKeyList = [-key for key in keyList]
    return encryptVigenereCipher(encryptedText, decryptKeyList)


# Simplified Enigma Cipher
def encryptSimpleEnigmaCipher(plainText, keys):
    encryptedText = ""
    for char in plainText:
        if char.isalpha():
            if char.isupper():
                encryptedText += keys[2][keys[1][keys[0].index(char.lower())].upper()]
            else:
                encryptedText += keys[2][keys[1][keys[0].index(char)]]
        else:
            encryptedText += char
    return encryptedText

def decryptSimpleEnigmaCipher(encryptedText, keys):
    decryptedText = ""
    for char in encryptedText:
        if char.isalpha():
            if char.isupper():
                decryptedText += chr

""" 
* Test your code with different input.
* Make sure it runs with `python` or `python3` command.
* Do not forget to remove unnecessary stuff before submitting your work.
"""

# Uncomment for testing.
key1 = "bcdefghijklmnopqrstuvwxyza"
key2 = "qwertyuioplkjhgfdsazxcvbnm"
key3 = "zxcvbnmlkjhgfdsaqwertyuiop"
# Encryption tests
print(encryptAtbashCipher("programming")) # kiltiznnrmt
print(encryptCaesarCipher("Cipher programming 101!",2)) # Ekrjgt rtqitcookpi 010?
print(encryptVigenereCipher("Cipher programming 101!",[1,3,2])) # Dlriht stpjtbpojqi 010?
#print(encryptSimpleEnigmaCipher("Cipher programming 101!",(key1,key2,key3))) # Wavsoz vznkzullamk 101!
# Decryption tests
print(decryptAtbashCipher(encryptAtbashCipher("programming")))
print(decryptCaesarCipher(encryptCaesarCipher("Cipher programming 101!",2),2))
print(decryptVigenereCipher(encryptVigenereCipher("Cipher programming 101!",[1,3,2]),[1,3,2]))
#print(decryptSimpleEnigmaCipher(encryptSimpleEnigmaCipher("Cipher programming 101!",(key1,key2,key3)),(key1,key2,key3)))
# # Comment out before submitting.
    

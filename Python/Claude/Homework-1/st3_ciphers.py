# Class - ciphers.py

# Easy
def encryptAtbashCipher(text):
    result = ""
    for char in text:
        if char.isalpha():
            if char.isupper():
                result += chr(90 - (ord(char) - 65))
            else:
                result += chr(122 - (ord(char) - 97))
        else:
            result += char
    return result

# Easy
def decryptAtbashCipher(text):
    return encryptAtbashCipher(text)

# Easy
def encryptCaesarCipher(text, key):
    result = ""
    mapping = {'.': ',', ',': '.', '!': '?', '?': '!', '0': '1', '1': '0', '2': '3', '3': '2', '4': '5', '5': '4', '6': '7', '7': '6', '8': '9', '9': '8'}
    for char in text:
        if char.isalpha():
            if char.isupper():
                result += chr((ord(char) - 65 + key) % 26 + 65)
            else:
                result += chr((ord(char) - 97 + key) % 26 + 97)
        else:
            result += mapping.get(char, char)
    return result

# Easy
def decryptCaesarCipher(text, key):
    return encryptCaesarCipher(text, -key)

# Medium
def encryptVigenereCipher(text, keyList):
    result = ""
    key_num = len(keyList)
    mapping = {'.': ',', ',': '.', '!': '?', '?': '!', '0': '1', '1': '0', '2': '3', '3': '2', '4': '5', '5': '4', '6': '7', '7': '6', '8': '9', '9': '8'}
    for i, char in enumerate(text):
        if char.isalpha():
            if char.isupper():
                result += chr((ord(char) - 65 + keyList[i % key_num]) % 26 + 65)
            else:
                result += chr((ord(char) - 97 + keyList[i % key_num]) % 26 + 97)
        else:
            result += mapping.get(char, char)
    return result

# Medium
def decryptVigenereCipher(text, keyList):
    negativeKeyList = [-key for key in keyList]
    return encryptVigenereCipher(text, negativeKeyList)

# Medium
def encryptSimpleEnigmaCipher(text, keys):
    result = ""
    for char in text:
        if char.isalpha():
            if char.isupper():
                result += keys[2][keys[1].index(keys[0][ord(char.lower()) - ord('a')])].upper()
            else:
                result += keys[2][keys[1].index(keys[0][ord(char) - ord('a')])]
        else:
            result += char
    return result

# Hard
def decryptSimpleEnigmaCipher(text, keys):
    key1, key2, key3 = keys
    reverseKey1 = [chr(ord('a') + key1.index(char)) for char in 'abcdefghijklmnopqrstuvwxyz']
    reverseKey2 = [chr(ord('a') + key2.index(char)) for char in 'abcdefghijklmnopqrstuvwxyz']
    reverseKey3 = [chr(ord('a') + key3.index(char)) for char in 'abcdefghijklmnopqrstuvwxyz']
    return encryptSimpleEnigmaCipher(text, (reverseKey3, reverseKey2, reverseKey1))

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
print(encryptAtbashCipher("programming"))  # kiltiznnrmt
print(encryptCaesarCipher("Cipher programming 101!", 2))  # Ekrjgt rtqitcookpi 010?
print(encryptVigenereCipher("Cipher programming 101!", [1, 3, 2]))  # Dlriht stpjtbpojqi 010?
print(encryptSimpleEnigmaCipher("Cipher programming 101!",(key1,key2,key3))) # Wavsoz vznkzullamk 101!
# Decryption tests
print(decryptAtbashCipher(encryptAtbashCipher("programming")))
print(decryptCaesarCipher(encryptCaesarCipher("Cipher programming 101!", 2), 2))
print(decryptVigenereCipher(encryptVigenereCipher("Cipher programming 101!", [1, 3, 2]), [1, 3, 2]))
print(decryptSimpleEnigmaCipher(encryptSimpleEnigmaCipher("Cipher programming 101!",(key1,key2,key3)),(key1,key2,key3)))
# # Comment out before submitting.

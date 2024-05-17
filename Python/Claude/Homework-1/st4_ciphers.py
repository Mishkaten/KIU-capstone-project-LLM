#cipher.py

# Easy
def encryptAtbashCipher(text):
    result = ""
    for char in text:
        if char.isalpha():
            if char.islower():
                result += chr(122 - (ord(char) - 97))
            else:
                result += chr(90 - (ord(char) - 65))
        else:
            result += char
    return result

# Easy
def decryptAtbashCipher(text):
    return encryptAtbashCipher(text)

# Easy
def encryptCaesarCipher(text, key):
    result = ""
    for char in text:
        if char.isalpha():
            if char.islower():
                result += chr((ord(char) - 97 + key) % 26 + 97)
            else:
                result += chr((ord(char) - 65 + key) % 26 + 65)
        else:
            result += mapChar(char)
    return result

# Easy
def decryptCaesarCipher(text, key):
    return encryptCaesarCipher(text, 26 - key)

# Medium
def encryptVigenereCipher(text, keyList):
    result = ""
    keyLen = len(keyList)
    for i, char in enumerate(text):
        if char.isalpha():
            key = keyList[i % keyLen]
            if char.islower():
                result += chr((ord(char) - 97 + key) % 26 + 97)
            else:
                result += chr((ord(char) - 65 + key) % 26 + 65)
        else:
            result += mapChar(char)
    return result

# Medium
def decryptVigenereCipher(text, keyList):
    decryptKeyList = [-key for key in keyList]
    return encryptVigenereCipher(text, decryptKeyList)

# Medium
def encryptSimpleEnigmaCipher(text, keys):
    result = ""
    for char in text:
        if char.isalpha():
            index = ord(char.lower()) - 97
            encryptedChar = keys[2][keys[1].index(keys[0][index])]
            if char.isupper():
                result += encryptedChar.upper()
            else:
                result += encryptedChar
        else:
            result += char
    return result

# Hard
def decryptSimpleEnigmaCipher(text, keys):
    decryptKeys = (
        keys[2],
        keys[1],
        keys[0]
    )
    return encryptSimpleEnigmaCipher(text, decryptKeys)

def mapChar(char):
    charMap = {
        '.': ',',
        ',': '.',
        '!': '?',
        '?': '!',
        '0': '1',
        '1': '0',
        '2': '3',
        '3': '2',
        '4': '5',
        '5': '4',
        '6': '7',
        '7': '6',
        '8': '9',
        '9': '8'
    }
    return charMap.get(char, char)

# Test cases
def run_tests():
    print("Testing Atbash Cipher:")
    atbash_plain = "atbash"
    atbash_encrypted = encryptAtbashCipher(atbash_plain)
    atbash_decrypted = decryptAtbashCipher(atbash_encrypted)
    print("Plain Text:", atbash_plain)
    print("Encrypted Text:", atbash_encrypted)
    print("Decrypted Text:", atbash_decrypted)
    print()

    print("Testing Caesar Cipher:")
    caesar_plain = "Cipher Programming - 101!"
    caesar_key = 3
    caesar_encrypted = encryptCaesarCipher(caesar_plain, caesar_key)
    caesar_decrypted = decryptCaesarCipher(caesar_encrypted, caesar_key)
    print("Plain Text:", caesar_plain)
    print("Encrypted Text:", caesar_encrypted)
    print("Decrypted Text:", caesar_decrypted)
    print()

    print("Testing Vigenère Cipher:")
    vigenere_plain = "Cipher Programming - 101!"
    vigenere_keys = [1, 3, 2]
    vigenere_encrypted = encryptVigenereCipher(vigenere_plain, vigenere_keys)
    vigenere_decrypted = decryptVigenereCipher(vigenere_encrypted, vigenere_keys)
    print("Plain Text:", vigenere_plain)
    print("Encrypted Text:", vigenere_encrypted)
    print("Decrypted Text:", vigenere_decrypted)
    print()

    print("Testing Simplified Enigma Cipher:")
    enigma_plain = "Cipher Programming - 101!"
    enigma_keys = ("bcdefghijklmnopqrstuvwxyza",
                   "qwertyuioplkjhgfdsazxcvbnm",
                   "zxcvbnmlkjhgfdsaqwertyuiop")
    enigma_encrypted = encryptSimpleEnigmaCipher(enigma_plain, enigma_keys)
    enigma_decrypted = decryptSimpleEnigmaCipher(enigma_encrypted, enigma_keys)
    print("Plain Text:", enigma_plain)
    print("Encrypted Text:", enigma_encrypted)
    print("Decrypted Text:", enigma_decrypted)
    print()

# Run the test function
run_tests()

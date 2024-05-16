# Cipher implementations
def encryptAtbashCipher(text):
    return ''.join(chr(219 - ord(char)) if 'a' <= char <= 'z' else char for char in text)

def decryptAtbashCipher(text):
    return encryptAtbashCipher(text)

def encryptCaesarCipher(text, key):
    key = key % 26
    result = []
    caesar_dict = {'.': ',', ',': '.', '!': '?', '?': '!', '0': '1', '1': '0', '2': '3', '3': '2', '4': '5', '5': '4', '6': '7', '7': '6', '8': '9', '9': '8'}
    for char in text:
        if char.isalpha():
            start = ord('a') if char.islower() else ord('A')
            shifted = chr(start + (ord(char) - start + key) % 26)
            result.append(shifted)
        elif char in caesar_dict:
            result.append(caesar_dict[char])
        else:
            result.append(char)
    return ''.join(result)

def decryptCaesarCipher(text, key):
    return encryptCaesarCipher(text, -key)

def encryptVigenereCipher(text, keyList):
    key_length = len(keyList)
    result = []
    for i, char in enumerate(text):
        if char.isalpha():
            key = keyList[i % key_length] % 26
            start = ord('a') if char.islower() else ord('A')
            shifted = chr(start + (ord(char) - start + key) % 26)
            result.append(shifted)
        else:
            result.append(char)
    return ''.join(result)

def decryptVigenereCipher(text, keyList):
    key_length = len(keyList)
    result = []
    for i, char in enumerate(text):
        if char.isalpha():
            key = -keyList[i % key_length] % 26
            start = ord('a') if char.islower() else ord('A')
            shifted = chr(start + (ord(char) - start + key) % 26)
            result.append(shifted)
        else:
            result.append(char)
    return ''.join(result)

def encryptSimpleEnigmaCipher(text, keys):
    result = list(text)
    for i in range(len(result)):
        char = result[i]
        if char.isalpha():
            for key in keys:
                index = ord(char.lower()) - ord('a')
                char = key[index]
                if result[i].isupper():
                    char = char.upper()
            result[i] = char
    return ''.join(result)

def decryptSimpleEnigmaCipher(text, keys):
    reversed_keys = keys[::-1]
    result = list(text)
    for i in range(len(result)):
        char = result[i]
        if char.isalpha():
            for key in reversed_keys:
                index = key.index(char.lower())
                char = chr(ord('a') + index)
                if result[i].isupper():
                    char = char.upper()
            result[i] = char
    return ''.join(result)

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

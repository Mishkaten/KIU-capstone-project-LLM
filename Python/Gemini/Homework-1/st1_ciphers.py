def encryptAtbashCipher(text):
    """Encrypts text using the Atbash cipher."""
    result = ""
    for char in text:
        if char.isalpha():
            base = ord('a') if char.islower() else ord('A')
            result += chr(base + 25 - (ord(char) - base))
        else:
            result += char
    return result

def decryptAtbashCipher(text):
    """Decrypts Atbash cipher text."""
    return encryptAtbashCipher(text)  # Atbash decryption is the same as encryption

def encryptCaesarCipher(text, key):
    """Encrypts text using the Caesar cipher with the given key."""
    result = ""
    for char in text:
        if char.isalpha():
            base = ord('a') if char.islower() else ord('A')
            result += chr((ord(char) - base + key) % 26 + base)
        elif char in ".!,?0123456789":
            mapping = {".": ",", ",": ".", "!": "?", "?": "!",
                       "0": "1", "1": "0", "2": "3", "3": "2",
                       "4": "5", "5": "4", "6": "7", "7": "6",
                       "8": "9", "9": "8"}
            result += mapping.get(char, char)  # Use mapping or keep unchanged
        else:
            result += char
    return result

def decryptCaesarCipher(text, key):
    """Decrypts Caesar cipher text with the given key."""
    return encryptCaesarCipher(text, -key)  # Decrypt by shifting in the opposite direction

def encryptVigenereCipher(text, keyList):
    """Encrypts text using the Vigenere cipher with the given key list."""
    result = ""
    key_len = len(keyList)
    for i, char in enumerate(text):
        if char.isalpha():
            base = ord('a') if char.islower() else ord('A')
            shift = keyList[i % key_len]
            result += chr((ord(char) - base + shift) % 26 + base)
        elif char in ".!,?0123456789":
            mapping = {".": ",", ",": ".", "!": "?", "?": "!",
                       "0": "1", "1": "0", "2": "3", "3": "2",
                       "4": "5", "5": "4", "6": "7", "7": "6",
                       "8": "9", "9": "8"}
            result += mapping.get(char, char)  # Use mapping or keep unchanged
        else:
            result += char
    return result

def decryptVigenereCipher(text, keyList):
    """Decrypts Vigenere cipher text with the given key list."""
    neg_keyList = [-k for k in keyList]
    return encryptVigenereCipher(text, neg_keyList)

def encryptSimpleEnigmaCipher(text, keys):
    """Encrypts text using a simplified Enigma cipher with the given keys."""
    result = ""
    for char in text:
        if char.isalpha():
            base = ord('a') if char.islower() else ord('A')
            index = ord(char) - base
            for key in keys:
                index = key.index(chr(index + base))
            result += chr(index + base)
        else:
            result += char
    return result

def decryptSimpleEnigmaCipher(text, keys):
    """Decrypts text encrypted with the simplified Enigma cipher."""
    result = ""
    for char in text:
        if char.isalpha():
            base = ord('a') if char.islower() else ord('A')
            index = ord(char) - base
            for key in reversed(keys):  # Reverse the keys for decryption
                index = ord(key[index]) - base
            result += chr(index + base)
        else:
            result += char
    return result


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
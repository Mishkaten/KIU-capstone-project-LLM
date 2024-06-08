# Easy
def encryptAtbashCipher(text):
    """Encrypts text using the Atbash cipher.

    Args:
        text (str): The plaintext to encrypt.

    Returns:
        str: The ciphertext.
    """
    result = ""
    for char in text:
        if char.isalpha():
            base = ord('a') if char.islower() else ord('A')
            result += chr(2 * base + 25 - ord(char))
        else:
            result += char
    return result


def decryptAtbashCipher(text):
    """Decrypts text using the Atbash cipher.

    Args:
        text (str): The ciphertext to decrypt.

    Returns:
        str: The plaintext.
    """
    return encryptAtbashCipher(text)  # Atbash decryption is the same as encryption


# Easy
def encryptCaesarCipher(text, key):
    """Encrypts text using the Caesar cipher.

    Args:
        text (str): The plaintext to encrypt.
        key (int): The shift value.

    Returns:
        str: The ciphertext.
    """
    result = ""
    symbol_map = {'.': ',', ',': '.', '!': '?', '?': '!', '0': '1', '1': '0', '2': '3', '3': '2', '4': '5', '5': '4',
                  '6': '7', '7': '6', '8': '9', '9': '8'}
    for char in text:
        if char.isalpha():
            base = ord('a') if char.islower() else ord('A')
            result += chr((ord(char) - base + key) % 26 + base)
        elif char in symbol_map:
            result += symbol_map[char]
        else:
            result += char
    return result


def decryptCaesarCipher(text, key):
    """Decrypts text using the Caesar cipher.

    Args:
        text (str): The ciphertext to decrypt.
        key (int): The shift value.

    Returns:
        str: The plaintext.
    """
    return encryptCaesarCipher(text, -key)  # Decrypt by shifting in the opposite direction


# Medium
def encryptVigenereCipher(text, keyList):
    """Encrypts text using the Vigenere cipher.

    Args:
        text (str): The plaintext to encrypt.
        keyList (list): A list of shift values.

    Returns:
        str: The ciphertext.
    """
    result = ""
    symbol_map = {'.': ',', ',': '.', '!': '?', '?': '!', '0': '1', '1': '0', '2': '3', '3': '2', '4': '5', '5': '4',
                  '6': '7', '7': '6', '8': '9', '9': '8'}
    key_len = len(keyList)
    for i, char in enumerate(text):
        key = keyList[i % key_len]
        if char.isalpha():
            base = ord('a') if char.islower() else ord('A')
            result += chr((ord(char) - base + key) % 26 + base)
        elif char in symbol_map:
            result += symbol_map[char]
        else:
            result += char
    return result


def decryptVigenereCipher(text, keyList):
    """Decrypts text using the Vigenere cipher.

    Args:
        text (str): The ciphertext to decrypt.
        keyList (list): A list of shift values.

    Returns:
        str: The plaintext.
    """
    neg_keyList = [-k for k in keyList]
    return encryptVigenereCipher(text, neg_keyList)  # Decrypt by using negative keys


# Medium
def encryptSimpleEnigmaCipher(text, keys):
    """Encrypts text using a simplified Enigma cipher.

    Args:
        text (str): The plaintext to encrypt.
        keys (tuple): A tuple of three cipher keys (strings).

    Returns:
        str: The ciphertext.
    """
    result = ""
    for char in text:
        if char.isalpha():
            for key in keys:
                char = key[ord(char.lower()) - ord('a')]
        result += char
    return result


def decryptSimpleEnigmaCipher(text, keys):
    """Decrypts text using a simplified Enigma cipher.

    Args:
        text (str): The ciphertext to decrypt.
        keys (tuple): A tuple of three cipher keys (strings).

    Returns:
        str: The plaintext.
    """
    result = ""
    for char in text:
        if char.isalpha():
            for key in reversed(keys):  # Reverse the keys for decryption
                char = chr(key.index(char.lower()) + ord('a'))
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
print(encryptSimpleEnigmaCipher("Cipher programming 101!", (key1, key2, key3)))  # Wavsoz vznkzullamk 101!
# Decryption tests
print(decryptAtbashCipher(encryptAtbashCipher("programming")))
print(decryptCaesarCipher(encryptCaesarCipher("Cipher programming 101!", 2), 2))
print(decryptVigenereCipher(encryptVigenereCipher("Cipher programming 101!", [1, 3, 2]), [1, 3, 2]))
print(decryptSimpleEnigmaCipher(encryptSimpleEnigmaCipher("Cipher programming 101!", (key1, key2, key3)),
                                (key1, key2, key3)))
# Comment out before submitting.


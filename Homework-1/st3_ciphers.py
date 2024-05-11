def encryptAtbashCipher(plain_text):
    return ''.join(chr(219 - ord(char)) if char.islower() else char for char in plain_text)

def decryptAtbashCipher(cipher_text):
    return encryptAtbashCipher(cipher_text)  # Since Atbash is symmetric
def encryptCaesarCipher(plain_text, shift):
    cipher_text = ''
    for char in plain_text:
        if char.isalpha():
            shift_base = 65 if char.isupper() else 97
            cipher_text += chr((ord(char) - shift_base + shift) % 26 + shift_base)
        else:
            cipher_text += char
    return cipher_text

def decryptCaesarCipher(cipher_text, shift):
    return encryptCaesarCipher(cipher_text, -shift)
def encryptVigenereCipher(plain_text, keys):
    cipher_text = ''
    key_length = len(keys)
    for i, char in enumerate(plain_text):
        if char.isalpha():
            shift_base = 65 if char.isupper() else 97
            key = keys[i % key_length]
            cipher_text += chr((ord(char) - shift_base + key) % 26 + shift_base)
        else:
            cipher_text += char
    return cipher_text

def decryptVigenereCipher(cipher_text, keys):
    return encryptVigenereCipher(cipher_text, [-k for k in keys])
def encryptSimpleEnigmaCipher(plain_text, keys):
    cipher_text = ''
    for char in plain_text:
        encrypted_char = char
        if char.isalpha():
            for key in keys:
                shift_base = 65 if char.isupper() else 97
                index = ord(encrypted_char) - shift_base
                encrypted_char = key[index]
        cipher_text += encrypted_char
    return cipher_text

def decryptSimpleEnigmaCipher(cipher_text, keys):
    decrypted_text = ''
    for char in cipher_text:
        decrypted_char = char
        if char.isalpha():
            for key in reversed(keys):
                shift_base = 65 if char.isupper() else 97
                index = key.index(decrypted_char)
                decrypted_char = chr(index + shift_base)
        decrypted_text += decrypted_char
    return decrypted_text
def test_atbash_cipher():
    print("Testing Atbash Cipher:")
    assert encryptAtbashCipher("atbash") == "zgyzhs", "Test Case 1 Failed"
    assert decryptAtbashCipher("zgyzhs") == "atbash", "Test Case 2 Failed"
    print("All Atbash Cipher tests passed!")

def test_caesar_cipher():
    print("Testing Caesar Cipher:")
    assert encryptCaesarCipher("abc", 3) == "def", "Test Case 1 Failed"
    assert decryptCaesarCipher("def", 3) == "abc", "Test Case 2 Failed"
    assert encryptCaesarCipher("xyz", 3) == "abc", "Test Case 3 Failed"
    assert decryptCaesarCipher("abc", 3) == "xyz", "Test Case 4 Failed"
    print("All Caesar Cipher tests passed!")

def test_vigenere_cipher():
    print("Testing Vigenere Cipher:")
    keys = [1, 3, 2]
    assert encryptVigenereCipher("cipher", keys) == "dlriht", "Test Case 1 Failed"
    assert decryptVigenereCipher("dlriht", keys) == "cipher", "Test Case 2 Failed"
    print("All Vigenere Cipher tests passed!")

def test_simplified_enigma_cipher():
    print("Testing Simplified Enigma Cipher:")
    keys = ("bcdefghijklmnopqrstuvwxyza", "zxcvbnmlkjhgfdsaqwertyuiop", "qwertyuioplkjhgfdsazxcvbnm")
    encrypted = encryptSimpleEnigmaCipher("abc", keys)
    decrypted = decryptSimpleEnigmaCipher(encrypted, keys)
    assert decrypted == "abc", "Test Case 1 Failed"
    print("All Simplified Enigma Cipher tests passed!")

def run_tests():
    test_atbash_cipher()
    test_caesar_cipher()
    test_vigenere_cipher()
    test_simplified_enigma_cipher()

run_tests()

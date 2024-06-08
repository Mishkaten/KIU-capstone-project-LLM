def encryptAtbashCipher(text):
    """Encrypts text using the Atbash cipher (a -> z, b -> y, etc.)."""
    mapping = str.maketrans("abcdefghijklmnopqrstuvwxyz", "zyxwvutsrqponmlkjihgfedcba")
    return text.translate(mapping)

def decryptAtbashCipher(text):
    """Decrypts Atbash cipher text."""
    return encryptAtbashCipher(text)  # Atbash decryption is the same as encryption

def encryptCaesarCipher(text, key):
    """Encrypts text using the Caesar cipher with the given shift key."""
    result = []
    for char in text:
        if char.isalpha():
            base = ord('a') if char.islower() else ord('A')
            result.append(chr((ord(char) - base + key) % 26 + base))
        elif char in ".!,?0123456789":  # Handle punctuation and numbers
            mapping = {
                '.': ',', ',': '.', '!': '?', '?': '!',
                '0': '1', '1': '0', '2': '3', '3': '2', '4': '5', '5': '4',
                '6': '7', '7': '6', '8': '9', '9': '8'
            }
            result.append(mapping.get(char, char))  # Use mapping or keep unchanged
        else:
            result.append(char)  # Keep other characters unchanged
    return "".join(result)

def decryptCaesarCipher(text, key):
    """Decrypts Caesar cipher text."""
    return encryptCaesarCipher(text, -key)  # Decrypt by shifting in the opposite direction

def encryptVigenereCipher(text, key_list):
    """Encrypts text using the Vigenere cipher with the given key list."""
    result = []
    key_len = len(key_list)
    for i, char in enumerate(text):
        shift = key_list[i % key_len]
        result.append(encryptCaesarCipher(char, shift))  # Reuse Caesar cipher logic
    return "".join(result)

def decryptVigenereCipher(text, key_list):
    """Decrypts Vigenere cipher text."""
    neg_key_list = [-key for key in key_list]
    return encryptVigenereCipher(text, neg_key_list)  # Decrypt by using negative keys

def encryptSimpleEnigmaCipher(text, keys):
    """Encrypts text using a simplified Enigma cipher with the given rotor keys."""
    result = []
    for char in text:
        if char.isalpha():
            for key in keys:
                char = key[ord(char.lower()) - ord('a')]  # Apply each rotor's mapping
        result.append(char)
    return "".join(result)

def decryptSimpleEnigmaCipher(text, keys):
    """Decrypts simplified Enigma cipher text."""
    # Create reverse mappings for each rotor
    reverse_keys = tuple("".join(chr(key.index(chr(i + ord('a'))) + ord('a')) for i in range(26)) for key in keys)
    return encryptSimpleEnigmaCipher(text, reverse_keys)  # Decrypt using reverse rotors


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

def encryptAtbashCipher(plain_text):
    result = ''
    for char in plain_text:
        if char.isalpha():
            result += chr(219 - ord(char)) if char.islower() else chr(155 - ord(char))
    return result

def decryptAtbashCipher(encrypted_text):
    # Since Atbash cipher is symmetric, encryption and decryption processes are the same.
    return encryptAtbashCipher(encrypted_text)

def encryptCaesarCipher(plain_text, key):
    result = ''
    for char in plain_text:
        if char.isalpha():
            shift = 65 if char.isupper() else 97
            result += chr((ord(char) - shift + key) % 26 + shift)
        elif char in char_map:
            result += char_map[char]
        else:
            result += char
    return result

def decryptCaesarCipher(encrypted_text, key):
    return encryptCaesarCipher(encrypted_text, -key)

char_map = {
    '.': ',', ',': '.', '!': '?', '?': '!', 
    '0': '1', '1': '0', '2': '3', '3': '2', 
    '4': '5', '5': '4', '6': '7', '7': '6', 
    '8': '9', '9': '8'
}

def encryptVigenereCipher(plain_text, keys):
    result = ''
    for i, char in enumerate(plain_text):
        if char.isalpha():
            shift = 65 if char.isupper() else 97
            key = keys[i % len(keys)]
            result += chr((ord(char) - shift + key) % 26 + shift)
        elif char in char_map:
            result += char_map[char]
        else:
            result += char
    return result

def decryptVigenereCipher(encrypted_text, keys):
    return encryptVigenereCipher(encrypted_text, [-k for k in keys])

def encryptSimpleEnigmaCipher(plain_text, keys):
    result = ''
    for i, char in enumerate(plain_text):
        transformed_char = char
        if char.isalpha():
            for key in keys:
                shift = 65 if char.isupper() else 97
                transformed_char = key[ord(transformed_char) - shift]
        result += transformed_char
    return result

def decryptSimpleEnigmaCipher(encrypted_text, keys):
    reverse_keys = [{v: k for k, v in enumerate(key)} for key in keys[::-1]]
    result = ''
    for i, char in enumerate(encrypted_text):
        transformed_char = char
        if char.isalpha():
            for key in reverse_keys:
                shift = 65 if char.isupper() else 97
                transformed_char = chr(key[transformed_char] + shift)
        result += transformed_char
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
    

import json

dictionary = {
    "password1": "fvrewcgreqw",
    "password2": "g542wsqfceaw",
    "password3": "gcwcgfdsab56",
    "password4": "fgcfehfdseg"
}

json_object = json.dumps(dictionary, indent = 4)

with open("test.json", "w") as outfile:
    outfile.write(json_object)


def print_hi(name):
    # Use a breakpoint in the code line below to debug your script.
    print(f'Hi, {name}')  # Press Ctrl+F8 to toggle the breakpoint.


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    print_hi('PyCharm')

# See PyCharm help at https://www.jetbrains.com/help/pycharm/

from PIL import Image

# load image
filename = "wisp.png"
img = Image.open(filename).convert("RGBA")
pixels = img.load()

# loop over every pixel
for y in range(img.height):
    for x in range(img.width):
        r, g, b, a = pixels[x, y]
        # brightness from black to white
        brightness = (r + g + b) / 3
        # set alpha based on brightness
        # 0 if black, 255 if white
        alpha = int(brightness)
        pixels[x, y] = (r, g, b, alpha)

# save result with real transparency
img.save(filename)

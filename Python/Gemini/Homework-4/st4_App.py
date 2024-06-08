import pygame
import random
import os

pygame.init()

# Constants
WIDTH, HEIGHT = 400, 600
FPS = 60
BIRD_SPEED = 3
PIPE_GAP = 150
PIPE_WIDTH = 50
PIPE_SPEED = 2
GRAVITY = 0.25
JUMP_STRENGTH = -6
WHITE = (255, 255, 255)
BLACK = (0, 0, 0)
GREEN = (0, 255, 0)

# Load images
bird_img = pygame.image.load("bird.png")  # Replace with your bird image
pipe_img = pygame.image.load("pipe.png")  # Replace with your pipe image
background_img = pygame.image.load("background.jpg")  # Replace with your background image

# Resize images
bird_img = pygame.transform.scale(bird_img, (30, 30))
pipe_img = pygame.transform.scale(pipe_img, (PIPE_WIDTH, 400))  # Adjust height as needed

# Sounds
flap_sound = pygame.mixer.Sound("crash.wav")  # Replace with your flap sound
hit_sound = pygame.mixer.Sound("eat.wav")  # Replace with your hit sound

# Initialize screen
screen = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("Flappy Bird")
clock = pygame.time.Clock()

# Bird class
class Bird:
    def __init__(self):
        self.x = 50
        self.y = HEIGHT // 2
        self.velocity = 0

    def draw(self):
        screen.blit(bird_img, (self.x, self.y))

    def update(self):
        self.velocity += GRAVITY
        self.y += self.velocity

    def jump(self):
        self.velocity = JUMP_STRENGTH
        flap_sound.play()

# Pipe class
class Pipe:
    def __init__(self, x):
        self.x = x
        self.top_pipe_height = random.randint(100, 350)
        self.bottom_pipe_height = HEIGHT - self.top_pipe_height - PIPE_GAP

    def draw(self):
        screen.blit(pipe_img, (self.x, 0), (0, 0, PIPE_WIDTH, self.top_pipe_height))
        screen.blit(pipe_img, (self.x, self.top_pipe_height + PIPE_GAP), (0, self.top_pipe_height, PIPE_WIDTH, self.bottom_pipe_height))

    def update(self):
        self.x -= PIPE_SPEED

# Game loop
def game_loop():
    bird = Bird()
    pipes = [Pipe(WIDTH + i * 200) for i in range(3)]
    score = 0
    running = True
    passed_pipes = set()  # Track pipes the bird has passed

    while running:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                running = False
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_SPACE:
                    bird.jump()

        bird.update()

        for pipe in pipes:
            pipe.update()
            if pipe.x + PIPE_WIDTH < 0:
                pipes.remove(pipe)
                pipes.append(Pipe(pipes[-1].x + 200))

            # Collision detection (simplified)
            if bird.x + bird_img.get_width() > pipe.x and bird.x < pipe.x + PIPE_WIDTH:
                if bird.y < pipe.top_pipe_height or bird.y + bird_img.get_height() > pipe.top_pipe_height + PIPE_GAP:
                    hit_sound.play()
                    running = False

            # Score update
            if bird.x > pipe.x + PIPE_WIDTH and pipe not in passed_pipes:
                passed_pipes.add(pipe)
                score += 1
                hit_sound.play()  # Play sound when passing pipe

        # Draw everything
        screen.blit(background_img, (0, 0))
        bird.draw()
        for pipe in pipes:
            pipe.draw()

        # Display score
        font = pygame.font.SysFont(None, 36)
        text = font.render(f"Score: {score}", True, WHITE)
        screen.blit(text, (10, 10))

        pygame.display.flip()
        clock.tick(FPS)

    pygame.quit()

if __name__ == "__main__":
    game_loop()

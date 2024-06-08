import pygame
import random


class Bird:
    def __init__(self, x, y):
        self.x = x
        self.y = y
        self.velocity = 0
        self.gravity = 0.25
        self.lift = -8

    def update(self):
        self.velocity += self.gravity
        self.y += self.velocity

    def jump(self):
        self.velocity = self.lift

    def draw(self, screen):
        pygame.draw.circle(screen, (255, 255, 0), (self.x, self.y), 20)  # Yellow circle



class Pipe:
    def __init__(self, x):
        self.x = x
        self.top_pipe_height = random.randint(100, 400)
        self.bottom_pipe_height = 600 - self.top_pipe_height - 150  # Gap between pipes
        self.width = 50
        self.passed = False

    def update(self):
        self.x -= 2  # Move pipes to the left

    def draw(self, screen):
        pygame.draw.rect(screen, (0, 255, 0), (self.x, 0, self.width, self.top_pipe_height))
        pygame.draw.rect(screen, (0, 255, 0), (self.x, self.top_pipe_height + 150, self.width, self.bottom_pipe_height))


class App:
    def init(self):
        # ... (previous code)
        self.bird = Bird(100, 350)
        self.pipes = [Pipe(400), Pipe(600), Pipe(800)]  # Initial pipes
        self.score = 0

    def update(self):
        self.events()
        self.bird.update()

        for pipe in self.pipes:
            pipe.update()
            if pipe.x + pipe.width < 0:  # Remove off-screen pipes
                self.pipes.remove(pipe)
                self.pipes.append(Pipe(800))  # Add new pipe

            # Collision detection (simplified)
            if self.bird.x + 20 > pipe.x and self.bird.x - 20 < pipe.x + pipe.width:
                if self.bird.y - 20 < pipe.top_pipe_height or self.bird.y + 20 > pipe.top_pipe_height + 150:
                    self.running = False

        # Check for spacebar or mouse click
        keys = pygame.key.get_pressed()
        if keys[pygame.K_SPACE] or pygame.mouse.get_pressed()[0]:
            self.bird.jump()

    def render(self):
        self.screen.fill((0, 0, 255))  # Blue background
        self.bird.draw(self.screen)
        for pipe in self.pipes:
            pipe.draw(self.screen)
        pygame.display.flip()

if __name__ == "__main__":
    app = App()
    app.run()
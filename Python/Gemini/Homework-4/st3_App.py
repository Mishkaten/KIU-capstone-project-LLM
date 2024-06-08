import pygame

class App:

    def __init__(self):
        self.running = False
        self.clock = None
        self.screen = None
        self.rect_x = 50
        self.rect_y = 50
        self.rect_speed = 5  # Speed of the moving rectangle

    def run(self):
        self.init()
        while self.running:
            self.update()
            self.render()
        self.cleanUp()

    def init(self):
        pygame.init()  # Initialize Pygame modules
        self.screen = pygame.display.set_mode((800, 600))  # Smaller window for the example
        pygame.display.set_caption("Simple Pygame Example")
        self.clock = pygame.time.Clock()
        self.running = True

    def update(self):
        self.events()
        self.move_rect()

    def events(self):
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                self.running = False

    def move_rect(self):
        """Example function to move the rectangle."""
        keys = pygame.key.get_pressed()
        if keys[pygame.K_LEFT]:
            self.rect_x -= self.rect_speed
        if keys[pygame.K_RIGHT]:
            self.rect_x += self.rect_speed
        if keys[pygame.K_UP]:
            self.rect_y -= self.rect_speed
        if keys[pygame.K_DOWN]:
            self.rect_y += self.rect_speed

        # Keep the rectangle within the screen boundaries
        self.rect_x = max(0, min(self.rect_x, 800 - 50))  # 50 is the rectangle width
        self.rect_y = max(0, min(self.rect_y, 600 - 50))  # 50 is the rectangle height

    def render(self):
        self.screen.fill((0, 0, 0))  # Clear the screen (black)
        pygame.draw.rect(self.screen, (255, 0, 0), (self.rect_x, self.rect_y, 50, 50))  # Draw a red rectangle
        pygame.display.flip()  # Update the entire display
        self.clock.tick(60)     # Limit to 60 FPS

    def cleanUp(self):
        pygame.quit()  # Uninitialize Pygame

if __name__ == "__main__":
    app = App()
    app.run()

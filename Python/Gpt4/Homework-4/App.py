import pygame

class App:
    
    def __init__(self):
        self.running = False
        self.clock = None
        self.screen = None

    def run(self):
        self.init()
        while self.running:
            self.update()
            self.render()
        self.cleanUp()

    def init(self):
        self.screen = pygame.display.set_mode((1200, 700))
        pygame.display.set_caption("TODO")

        self.clock = pygame.time.Clock()
        self.running = True

    def update(self):
        self.events()

    def events(self):
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                self.running = False

    def render(self):
        pass

    def cleanUp(self):
        pass

if __name__ == "__main__":
    app = App()
    app.run()
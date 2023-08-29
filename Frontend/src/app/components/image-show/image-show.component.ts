import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Image } from '../../models/image.model';
import { ImageService } from '../../services/image.service';

@Component({
  selector: 'app-image-show',
  templateUrl: './image-show.component.html',
  styleUrls: ['./image-show.component.scss']
})
export class ImageShowComponent {
  @Input() image: Image = {
    filename: '',
    contentType: '',
    url: ''
  };

  constructor(
    private imageService: ImageService,
    private route: ActivatedRoute,
    private router: Router,
    ) {}

  ngOnInit(): void {
    this.getImage(this.route.snapshot.params["id"]);
  }

  deleteImage(): void {
    this.imageService.deleteImage(this.image.id)
    .subscribe({
      next: (res) => {
        console.log(res);
        this.router.navigate(["/images"]);
      },
      error: (e) => console.error(e)
    });
  }

  getImage(id: string): void {
    this.imageService.getImage(id)
    .subscribe({
      next: (data) => {
        this.image = data;
        console.log('Image', data);
      },
      error: (e) => console.error(e)
    })
  }
}

import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
             selector: 'app-admin-panel',
             templateUrl: './admin-panel.component.html',
             styleUrls: ['./admin-panel.component.css']
           })
export class AdminPanelComponent implements OnInit {
  title: string;
  private buttons: ({ link: string; name: string })[];


  constructor(private route: ActivatedRoute, private router: Router) {
    this.title = 'Admin Panel';
    this.buttons = [
      {link: '/admin/cards', name: 'Cards'},
      {link: '/admin/users', name: 'Users'},
      {link: '/admin/adduser', name: 'Add User'}
    ]
  }

  ngOnInit() {
    this.router.navigate(['/admin/cards']);
  }

}

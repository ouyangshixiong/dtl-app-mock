import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISiteInfo } from 'app/shared/model/site-info.model';

@Component({
    selector: 'jhi-site-info-detail',
    templateUrl: './site-info-detail.component.html'
})
export class SiteInfoDetailComponent implements OnInit {
    siteInfo: ISiteInfo;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ siteInfo }) => {
            this.siteInfo = siteInfo;
        });
    }

    previousState() {
        window.history.back();
    }
}

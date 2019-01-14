import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEnterpriseInfo } from 'app/shared/model/enterprise-info.model';

@Component({
    selector: 'jhi-enterprise-info-detail',
    templateUrl: './enterprise-info-detail.component.html'
})
export class EnterpriseInfoDetailComponent implements OnInit {
    enterpriseInfo: IEnterpriseInfo;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ enterpriseInfo }) => {
            this.enterpriseInfo = enterpriseInfo;
        });
    }

    previousState() {
        window.history.back();
    }
}

import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEnterpriseInfo } from 'app/shared/model/enterprise-info.model';
import { EnterpriseInfoService } from './enterprise-info.service';

@Component({
    selector: 'jhi-enterprise-info-delete-dialog',
    templateUrl: './enterprise-info-delete-dialog.component.html'
})
export class EnterpriseInfoDeleteDialogComponent {
    enterpriseInfo: IEnterpriseInfo;

    constructor(
        protected enterpriseInfoService: EnterpriseInfoService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.enterpriseInfoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'enterpriseInfoListModification',
                content: 'Deleted an enterpriseInfo'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-enterprise-info-delete-popup',
    template: ''
})
export class EnterpriseInfoDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ enterpriseInfo }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EnterpriseInfoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.enterpriseInfo = enterpriseInfo;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}

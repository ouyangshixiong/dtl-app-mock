import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISiteInfo } from 'app/shared/model/site-info.model';
import { SiteInfoService } from './site-info.service';

@Component({
    selector: 'jhi-site-info-delete-dialog',
    templateUrl: './site-info-delete-dialog.component.html'
})
export class SiteInfoDeleteDialogComponent {
    siteInfo: ISiteInfo;

    constructor(protected siteInfoService: SiteInfoService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.siteInfoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'siteInfoListModification',
                content: 'Deleted an siteInfo'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-site-info-delete-popup',
    template: ''
})
export class SiteInfoDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ siteInfo }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SiteInfoDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.siteInfo = siteInfo;
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

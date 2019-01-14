import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRealnameInfo } from 'app/shared/model/realname-info.model';
import { RealnameInfoService } from './realname-info.service';

@Component({
    selector: 'jhi-realname-info-delete-dialog',
    templateUrl: './realname-info-delete-dialog.component.html'
})
export class RealnameInfoDeleteDialogComponent {
    realnameInfo: IRealnameInfo;

    constructor(
        protected realnameInfoService: RealnameInfoService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.realnameInfoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'realnameInfoListModification',
                content: 'Deleted an realnameInfo'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-realname-info-delete-popup',
    template: ''
})
export class RealnameInfoDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ realnameInfo }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RealnameInfoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.realnameInfo = realnameInfo;
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

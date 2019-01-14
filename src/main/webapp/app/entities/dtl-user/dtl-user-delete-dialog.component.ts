import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDTLUser } from 'app/shared/model/dtl-user.model';
import { DTLUserService } from './dtl-user.service';

@Component({
    selector: 'jhi-dtl-user-delete-dialog',
    templateUrl: './dtl-user-delete-dialog.component.html'
})
export class DTLUserDeleteDialogComponent {
    dTLUser: IDTLUser;

    constructor(protected dTLUserService: DTLUserService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.dTLUserService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'dTLUserListModification',
                content: 'Deleted an dTLUser'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-dtl-user-delete-popup',
    template: ''
})
export class DTLUserDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ dTLUser }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DTLUserDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.dTLUser = dTLUser;
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

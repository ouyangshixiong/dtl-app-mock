/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DtlAppMockTestModule } from '../../../test.module';
import { StoreUserDeleteDialogComponent } from 'app/entities/store-user/store-user-delete-dialog.component';
import { StoreUserService } from 'app/entities/store-user/store-user.service';

describe('Component Tests', () => {
    describe('StoreUser Management Delete Component', () => {
        let comp: StoreUserDeleteDialogComponent;
        let fixture: ComponentFixture<StoreUserDeleteDialogComponent>;
        let service: StoreUserService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [StoreUserDeleteDialogComponent]
            })
                .overrideTemplate(StoreUserDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(StoreUserDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StoreUserService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
